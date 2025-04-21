import { Form, redirect, useSearchParams } from "react-router-dom";
import { FormInput, SubmitBtn } from "../components";
import { customFetch } from "../utils";
import { toast } from "react-toastify";

export const action = async ({ request }) => {
  const formData = await request.formData();
  const data = Object.fromEntries(formData);

  try {
    const response = await customFetch.post(
      `/auth/reset-password?email=${encodeURIComponent(
        data.email
      )}&resetToken=${encodeURIComponent(
        data.resetToken
      )}&newPassword=${encodeURIComponent(data.newPassword)}`
    );
    toast.success("Password changed successfully.");
    return redirect("/login");
  } catch (error) {
    const errorMessage =
      error?.response?.data?.error?.message ||
      "please enter the correct reset token";
    toast.error(errorMessage);
    return null;
  }
};

const ResetPassword = () => {
  const [searchParams] = useSearchParams();
  const email = searchParams.get("email"); // Get email from URL

  return (
    <section className="min-h-screen flex items-center justify-center bg-gradient-to-br from-base-100 to-base-300 px-4 py-12">
      <Form
        method="POST"
        className="card w-full max-w-md bg-base-100 shadow-2xl p-8 rounded-2xl flex flex-col gap-y-5"
      >
        <h4 className="text-center text-3xl font-extrabold text-primary">
          Reset Password
        </h4>

        <FormInput type="hidden" name="email" value={email} />
        <FormInput type="text" label="Reset Token" name="resetToken" />
        <FormInput type="password" label="password" name="newPassword" />

        <div className="mt-4">
          <SubmitBtn text="Submit" />
        </div>
      </Form>
    </section>
  );
};
export default ResetPassword;
