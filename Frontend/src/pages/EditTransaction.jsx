import { toast } from "react-toastify";
import { customFetch } from "../utils";
import { Form, redirect, useLoaderData } from "react-router-dom";
import { FormInput, SubmitBtn } from "../components";

export const loader =
  (store) =>
  async ({ params }) => {
    const user = store.getState().userState.user;

    if (!user) {
      toast.warn("Please login first");
      return redirect("/login");
    }

    try {
      const { data } = await customFetch.get(`/transactions/${params.id}`);
      return data;
    } catch (error) {
      toast.error(error?.response?.data?.message);
      return redirect("/dashboard/transactions");
    }
  };

export const action = async ({ request, params }) => {
  const formData = await request.formData();
  const data = Object.fromEntries(formData);
  console.log(data);

  try {
    const response = await customFetch.put(`/transactions/${params.id}`, data, {
      headers: { "Content-Type": "application/json" },
    });

    toast.success("transaction edited successfully");
    return redirect("/dashboard/transactions");
  } catch (error) {
    const errorMessage =
      error?.response?.data?.error?.message || "something went wrong...";
    toast.error(errorMessage);
    return null;
  }
};

const categories = [
  "FOOD",
  "TRANSPORT",
  "RENT",
  "BOOKS",
  "SHOPPING",
  "SALARY",
  "POCKET_MONEY",
  "OTHERS",
];

const types = ["INCOME", "EXPENSE"];

const formatOption = (option) =>
  option
    .toLowerCase()
    .replace(/_/g, " ")
    .replace(/\b\w/g, (char) => char.toUpperCase());

const EditTransaction = () => {
  const { data } = useLoaderData();

  return (
    <Form method="post" className="form">
      <h4 className="text-3xl flex justify-center mb-8 capitalize ">
        edit job
      </h4>
      <div className="bg-base-200 rounded-md px-8 py-4 grid gap-x-10 gap-y-8 sm:grid-cols-2 items-center">
        {/* CATEGORY */}
        <div className="form-control">
          <label className="label" htmlFor="category">
            <span className="label-text">Category*</span>
          </label>
          <select
            name="category"
            id="category"
            className="select select-sm w-full"
            defaultValue={data.category}
            required
          >
            <option value="" disabled>
              Select Category
            </option>
            {categories.map((item) => (
              <option key={item} value={item}>
                {formatOption(item)}
              </option>
            ))}
          </select>
        </div>

        {/* TYPE */}
        <div className="form-control">
          <label className="label" htmlFor="type">
            <span className="label-text">Type*</span>
          </label>
          <select
            name="type"
            id="type"
            className="select select-sm w-full"
            defaultValue={data.type}
            required
          >
            <option value="" disabled>
              Select Type
            </option>
            {types.map((item) => (
              <option key={item} value={item}>
                {formatOption(item)}
              </option>
            ))}
          </select>
        </div>

        {/* AMOUNT */}
        <FormInput
          type="number"
          label="Amount*"
          name="amount"
          size="input-sm"
          value={data.amount}
          required
        />
        <FormInput
          type="text"
          label="Description"
          name="description"
          size="input-sm"
          value={data.description}
        />
        <FormInput
          type="date"
          label="Date*"
          name="date"
          size="input-sm"
          value={data.date}
          required
        />

        <div className="col-span-full">
          <SubmitBtn text="Add Transaction" className="w-full" />
        </div>
      </div>
    </Form>
  );
};
export default EditTransaction;
