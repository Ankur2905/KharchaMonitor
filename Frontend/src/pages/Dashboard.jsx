import { redirect, useLoaderData } from "react-router-dom";
import { DashboardStats } from "../components";
import { toast } from "react-toastify";
import { customFetch } from "../utils";

export const loader = (store) => async () => {
  const user = store.getState().userState.user;

  if (!user) {
    toast.warn("Please login first");
    return redirect("/login");
  }

  const userId = user?.budget?.userId;

  try {
    const { data } = await customFetch.get(
      `/transactions/user/${user.username}/details`
    );
    return { data };
  } catch (error) {
    toast.error(error?.response?.data?.message || "Something went wrong");
    return;
  }
};

const Dashboard = () => {
  const { data } = useLoaderData();
  const budget = data[0];
  const earnings = data[1];
  const expenses = data[2];

  return (
    <>
      <h1 className="text-2xl font-semibold flex justify-center">Dashboard</h1>
      <DashboardStats budget={budget} earnings={earnings} expenses={expenses} />
    </>
  );
};

export default Dashboard;
