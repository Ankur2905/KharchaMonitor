import { Form, redirect, useLoaderData } from "react-router-dom";
import { toast } from "react-toastify";
import { BudgetInput } from "../components";
import { customFetch } from "../utils";

export const loader = (store) => () => {
  const user = store.getState().userState.user;

  if (!user) {
    toast.warn("Please login first");
    return redirect("/login");
  }

  const { budget } = user;

  return { budgetId: budget.id };
};

export const action =
  (store) =>
  async ({ request }) => {
    const formData = await request.formData();
    const budgetId = formData.get("id");
    const data = Object.fromEntries(formData);
    delete data.id;

    try {
      const response = await customFetch.put(`/budget/${budgetId}`, data, {
        headers: { "Content-Type": "application/json" },
      });

      toast.success("Budget updated successfully");
      return redirect("/");
    } catch (error) {
      const errorMessage =
        error?.response?.data?.error?.message || "something went wrong...";
      toast.error(errorMessage);
      return null;
    }
  };

const BudgetManagement = () => {
  const { budgetId } = useLoaderData();
  return (
    <Form method="post">
      <input type="hidden" name="id" value={budgetId} />
      <div className="flex flex-col items-center flex-grow">
        <h1 className="text-2xl font-semibold">Update Budget</h1>
        <div className="w-full max-w-xl mx-4 mt-6 bg-base-200 p-6 rounded-lg shadow-lg">
          <BudgetInput />
        </div>
      </div>
    </Form>
  );
};
export default BudgetManagement;
