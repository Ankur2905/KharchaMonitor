import { Form } from "react-router-dom";
import FormInput from "./FormInput";
import SubmitBtn from "./SubmitBtn";

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

const TransactionInput = () => {
  return (
    <Form className="bg-base-200 rounded-md px-8 py-4 grid gap-x-10 gap-y-8 sm:grid-cols-2 items-center">
      {/* CATEGORY */}
      <div className="form-control">
        <label className="label" htmlFor="category">
          <span className="label-text">Category*</span>
        </label>
        <select
          name="category"
          id="category"
          className="select select-sm w-full"
          defaultValue=""
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
          defaultValue=""
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
        required
      />
      <FormInput
        type="text"
        label="Description"
        name="description"
        size="input-sm"
      />
      <FormInput
        type="date"
        label="Date*"
        name="date"
        size="input-sm"
        required
      />

      <div className="col-span-full">
        <SubmitBtn text="Add Transaction" className="w-full" />
      </div>
    </Form>
  );
};

export default TransactionInput;
