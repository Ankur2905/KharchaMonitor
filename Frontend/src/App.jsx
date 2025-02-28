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
} from "./pages";

import { ErrorElement } from "./components";

// loaders

// actions
import { action as registerAction } from "./pages/Register";
import { action as verifyOtpAction } from "./pages/VerifyOtp";
import { action as loginAction } from "./pages/Login";
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
      },
      {
        path: "addTransaction",
        element: <AddTransaction />,
      },
      {
        path: "addBudget",
        element: <BudgetManagement />,
      },
      {
        path: "transactions",
        element: <Transactions />,
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
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
