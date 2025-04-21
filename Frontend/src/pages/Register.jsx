import { Form, Link, redirect, useNavigation } from "react-router-dom";
import { FormInput, SubmitBtn } from "../components";
import { customFetch } from "../utils";
import { toast } from "react-toastify";

export const action = async ({ request }) => {
  const formData = await request.formData();
  const data = Object.fromEntries(formData);

  try {
    const response = await customFetch.post("/auth/signup", data, {
      headers: { "Content-Type": "application/json" },
    });
    toast.success("User registered. Please verify with OTP sent to your registered email");
    return redirect(`/verify?email=${encodeURIComponent(data.email)}`);
  } catch (error) {
    const errorMessage =
      error?.response?.data?.error?.message ||
      "please double check your credentials";
    toast.error(errorMessage);
    return null;
  }
};

const Register = () => {
  const navigation = useNavigation();
  const isSubmitting = navigation.state === "submitting";
  return (
    <section className="min-h-screen flex items-center justify-center bg-gradient-to-br from-base-100 to-base-300 px-4 py-12">
      <Form
        method="POST"
        className="card w-full max-w-md bg-base-100 shadow-2xl p-8 rounded-2xl flex flex-col gap-y-5"
      >
        <h4 className="text-center text-3xl font-extrabold text-primary">
          Register
        </h4>

        <FormInput type="text" label="username" name="username" />
        <FormInput type="email" label="email" name="email" />
        <FormInput type="password" label="password" name="password" />

        <div className="mt-4">
          <SubmitBtn
            text={isSubmitting ? "Registering..." : "Register"}
            disabled={isSubmitting}
          />
        </div>

        <p className="text-center text-sm text-base-content/80">
          Already a member?
          <Link
            to="/login"
            className="ml-2 text-primary hover:underline font-medium"
          >
            Login
          </Link>
        </p>
      </Form>
    </section>
  );
};
export default Register;
