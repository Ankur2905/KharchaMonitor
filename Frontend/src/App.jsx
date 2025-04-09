import { RouterProvider, createBrowserRouter } from "react-router-dom";

import {
  About,
  AddTransaction,
  BudgetManagement,
  Dashboard,
  HomeLayout,
  Transactions,
  Error,
  Login,
  Register,
  LandingPage,
  VerifyOtp,
  ForgotPassword,
  ResetPassword,
} from "./pages";

import { ErrorElement } from "./components";

// loaders
import { loader as addTransactionLoader } from "./pages/AddTransaction";
import { loader as budgetMAnagementLoader } from "./pages/BudgetManagement";
import { loader as dashboardLoader } from "./pages/Dashboard";
import { loader as transactionsLoader } from "./pages/Transactions";

// actions
import { action as registerAction } from "./pages/Register";
import { action as verifyOtpAction } from "./pages/VerifyOtp";
import { action as loginAction } from "./pages/Login";
import { action as forgotPasswordAction } from "./pages/ForgotPassword";
import { action as resetPasswordAction } from "./pages/ResetPassword";
import { action as budgetAction } from "./pages/BudgetManagement";
import { action as addTransactionAction } from "./pages/AddTransaction";

import { store } from "./store";

const router = createBrowserRouter([
  {
    path: "/",
    element: <HomeLayout />,
    errorElement: <Error />,
    children: [
      {
        index: true,
        element: <LandingPage />,
        errorElement: <ErrorElement />,
      },
      {
        path: "dashboard",
        element: <Dashboard />,
        loader: dashboardLoader(store),
      },
      {
        path: "addTransaction",
        element: <AddTransaction />,
        loader: addTransactionLoader(store),
        action: addTransactionAction(store),
      },
      {
        path: "addBudget",
        element: <BudgetManagement />,
        loader: budgetMAnagementLoader(store),
        action: budgetAction(store),
      },
      {
        path: "transactions",
        element: <Transactions />,
        loader: transactionsLoader(store),

      },
      {
        path: "about",
        element: <About />,
      },
    ],
  },
  {
    path: "/login",
    element: <Login />,
    errorElement: <ErrorElement />,
    action: loginAction(store),
  },
  {
    path: "/register",
    element: <Register />,
    errorElement: <ErrorElement />,
    action: registerAction,
  },
  {
    path: "/verify",
    element: <VerifyOtp />,
    errorElement: <ErrorElement />,
    action: verifyOtpAction,
  },
  {
    path: "/forgotPassword",
    element: <ForgotPassword />,
    errorElement: <ErrorElement />,
    action: forgotPasswordAction,
  },
  {
    path: "/resetPassword",
    element: <ResetPassword />,
    errorElement: <ErrorElement />,
    action: resetPasswordAction,
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
