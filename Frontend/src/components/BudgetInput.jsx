import FormInput from "./FormInput";
import SubmitBtn from "./SubmitBtn";

const BudgetInput = () => {
  return (
    <div className="bg-base-200 rounded-md px-8 py-4 grid gap-x-10 gap-y-8 sm:grid-cols-2 items-center">
      {/* AMOUNT */}
      <FormInput type="number" label="amount*" name="amount" size="input-sm" required/>
      <FormInput
        type="date"
        label="start date*"
        name="startDate"
        size="input-sm"
        required
      />
      <FormInput
        type="date"
        label="end date*"
        name="endDate"
        size="input-sm"
        required
      />
      <div className="col-span-full">
        <SubmitBtn text="Update budget" className="w-full" />
      </div>
    </div>
  );
};
export default BudgetInput;
