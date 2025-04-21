import { redirect, useLoaderData } from "react-router-dom";
import { toast } from "react-toastify";
import { TransactionsList } from "../components";

export const loader = (store) => () => {
  const user = store.getState().userState.user;

  if (!user) {
    toast.warn("Please login first");
    return redirect("/login");
  }

  const { username } = user;

  return { username };
};

const Transactions = () => {
  const { username } = useLoaderData();

  return (
    <>
      <TransactionsList />
    </>
  );
};
export default Transactions;
