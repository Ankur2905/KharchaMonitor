import { Form, Link, redirect, useNavigation } from "react-router-dom";
import { FormInput, SubmitBtn } from "../components";
import { toast } from "react-toastify";
import { customFetch } from "../utils";
import { loginUser } from "../features/user/userSlice";

export const action =
  (store) =>
  async ({ request }) => {
    const formData = await request.formData();
    const data = Object.fromEntries(formData);

    try {
      const response = await customFetch.post("/auth/login", data, {
        headers: { "Content-Type": "application/json" },
      });

      const {token, user} = response.data.data;

      store.dispatch(loginUser({user, token}));
      toast.success("Logged in successfully");
      return redirect("/dashboard");
    } catch (error) {
      const errorMessage =
        error?.response?.data?.error?.message ||
        "Incorrect username or password";
      toast.error(errorMessage);
      return null;
    }
  };

const Login = () => {
  const navigation = useNavigation();
  const isSubmitting = navigation.state === "submitting";
  return (
    <section className="min-h-screen flex items-center justify-center bg-gradient-to-br from-base-100 to-base-300 px-4 py-12">
      <Form
        method="POST"
        className="card w-full max-w-md bg-base-100 shadow-2xl p-8 rounded-2xl flex flex-col gap-y-5"
      >
        <h4 className="text-center text-3xl font-extrabold text-primary">
          Login
        </h4>

        <FormInput type="text" label="Username" name="username" />
        <FormInput type="password" label="Password" name="password" />

        <div className="mt-4">
          <SubmitBtn
            text={isSubmitting ? "Submitting..." : "Login"}
            disabled={isSubmitting}
          />
        </div>

        <p className="text-center text-sm text-base-content/80">
          Not a member yet?
          <Link
            to="/register"
            className="ml-2 text-primary hover:underline font-medium"
          >
            Register
          </Link>
        </p>

        <p className="text-center text-sm text-base-content/80 -mt-2">
          Forgot your password?
          <Link
            to="/forgotPassword"
            className="ml-2 text-secondary hover:underline font-medium"
          >
            Reset it here
          </Link>
        </p>
      </Form>
    </section>
  );
};
export default Login;
