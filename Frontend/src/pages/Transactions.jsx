import { redirect } from "react-router-dom";
import { toast } from "react-toastify"

export const loader = (store) => () => {
  const user = store.getState().userState.user;

  if(!user) {
    toast.warn("Please login first");
    return redirect("/login");
  }
  return null;
}

const Transactions = () => {
  return (
    <div>Transactions</div>
  )
}
export default Transactions