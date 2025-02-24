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
} from "./pages";

const router = createBrowserRouter([
  {
    path: "/",
    element: <HomeLayout />,
    errorElement: <Error />,
    children: [
      {
        index: true,
        element: <LandingPage />,
      },
      {
        path: "dashboard",
        element: <Dashboard />,
      },
      {
        path: "addTransaction",
        element: <AddTransaction />
      },
      {
        path: "addBudget",
        element: <BudgetManagement />
      },
      {
        path: "transactions",
        element: <Transactions />
      },
      {
        path: "about",
        element: <About />
      }
    ],
  },
  {
    path: "/login",
    element: <Login />,
    errorElement: <Error />,
  },
  {
    path: "/register",
    element: <Register />,
    errorElement: <Error />,
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
