import { Form, redirect, useLoaderData } from "react-router-dom";
import { toast } from "react-toastify";
import { TransactionInput } from "../components";
import { customFetch } from "../utils";

export const loader = (store) => () => {
  const user = store.getState().userState.user;

  if (!user) {
    toast.warn("Please login first");
    return redirect("/login");
  }

  const { budget } = user;
  
  return { userId: budget.userId };
};

export const action =
  (store) =>
  async ({ request }) => {
    const formData = await request.formData();
    const userId = formData.get("userId");
    const data = Object.fromEntries(formData);
    data.userId = userId

    try {
      const response = await customFetch.post("/transactions", data, {
        headers: { "Content-Type": "application/json" },
      });

      toast.success("transaction added successfully");
      return redirect("/");
    } catch (error) {
      const errorMessage =
        error?.response?.data?.error?.message || "something went wrong...";
      toast.error(errorMessage);
      return null;
    }
  };

const AddTransaction = () => {
  const {userId} = useLoaderData();
  return (
    <Form method="post">
      <input type="hidden" name="userId" value={userId} />
      <div className="flex flex-col items-center flex-grow">
        <h1 className="text-2xl font-semibold">Add Transaction</h1>
        <div className="w-full max-w-xl mx-4 mt-6 bg-base-200 p-6 rounded-lg shadow-lg">
          <TransactionInput />
        </div>
      </div>
    </Form>
  );
};
export default AddTransaction;
