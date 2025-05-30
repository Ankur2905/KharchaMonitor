import { redirect, useLoaderData } from "react-router-dom";
import { toast } from "react-toastify";
import { Pagination, SectionTitle, TransactionsList } from "../components";
import { customFetch } from "../utils";

export const loader =
  (store) =>
  async ({ request }) => {
    const user = store.getState().userState.user;

    if (!user) {
      toast.warn("Please login first");
      return redirect("/login");
    }

    const url = new URL(request.url);
    const page = url.searchParams.get("page") || 0;
    const size = url.searchParams.get("size") || 6;

    try {
      const response = await customFetch.get(
        `/transactions/user/${user.username}?page=${page}&size=${size}`
      );    
      
      return { data: response.data.data};
    } catch (error) {
      console.log(error);
      const errorMessage =
        error?.response?.data?.error?.message ||
        "There was an error";
      toast.error(errorMessage);
      if (error?.response?.status === 401 || 403) return redirect("/login");
    }
    return null;
  };

const Transactions = () => {
  const { data } = useLoaderData();
  
  if (data.totalElements < 1) {
    return (
      <div className="flex flex-col items-center justify-center h-64 text-center px-4">
        <h2 className="text-4xl font-semibold ">
          No Transactions Found
        </h2>
        <p className="mt-2 text-lg ">You have not added any transactions.</p>
      </div>
    );
  }
  

  return (
    <>
      <h1 className="text-2xl font-semibold flex justify-center">Your Transactions</h1>
      <TransactionsList />
      <Pagination />
    </>
  );
};
export default Transactions;
