import { redirect } from "react-router-dom";
import { toast } from "react-toastify"
import { BudgetInput } from "../components";

export const loader = (store) => () => {
  const user = store.getState().userState.user;

  if(!user) {
    toast.warn("Please login first");
    return redirect("/login");
  }
  return null;
}

const BudgetManagement = () => {
  return (
    <div className="flex flex-col items-center flex-grow">
      <h1 className="text-2xl font-semibold">Update Budget</h1>
      <div className="w-full max-w-xl mx-4 mt-6 bg-base-200 p-6 rounded-lg shadow-lg">
        <BudgetInput />
      </div>
    </div>
  );
}
export default BudgetManagement