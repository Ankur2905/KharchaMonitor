import { redirect, useLoaderData } from "react-router-dom";
import { toast } from "react-toastify";
import { SectionTitle, TransactionsList } from "../components";
import { customFetch } from "../utils";

export const loader =
  (store) =>
  async ({ request }) => {
    const user = store.getState().userState.user;

    if (!user) {
      toast.warn("Please login first");
      return redirect("/login");
    }


    try {
      const response = await customFetch.get(
        `/transactions/user/${user.username}`
      );
      return { transactions: response.data.data };
    } catch (error) {
      console.log(error);
      const errorMessage =
        error?.response?.data?.error?.message ||
        "There was an error placing your order";
      toast.error(errorMessage);
      if (error?.response?.status === 401 || 403) return redirect("/login");
    }
    return null;
  };

const Transactions = () => {
  const { transactions } = useLoaderData();
  if (transactions.length < 1) {
    return <SectionTitle text="No transactions available" />;
  }

  return (
    <>
      <SectionTitle text="Your transactions" />
      <TransactionsList />
    </>
  );
};
export default Transactions;
