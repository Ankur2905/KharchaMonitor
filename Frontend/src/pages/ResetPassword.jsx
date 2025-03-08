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
    <section className="h-screen grid place-items-center">
      <Form
        method="POST"
        className="card w-96 p-8 bg-base-300 shadow-lg  flex flex-col gap-y-4"
      >
        <h4 className="text-center text-3xl font-bold">Reset Password</h4>
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
