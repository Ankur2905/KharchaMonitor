import { Form, redirect, useNavigation } from "react-router-dom";
import { FormInput, SubmitBtn } from "../components";
import { customFetch } from "../utils";
import { toast } from "react-toastify";

export const action = async ({ request }) => {
  const formData = await request.formData();
  const data = Object.fromEntries(formData);

  try {
    const response = await customFetch.post(
      `/auth/forgot-password?email=${encodeURIComponent(data.email)}`
    );

    toast.success("Reset token sent to the registered email.");
    return redirect(`/resetPassword?email=${encodeURIComponent(data.email)}`);
  } catch (error) {
    const errorMessage =
      error?.response?.data?.error?.message || "please enter the correct email";
    toast.error(errorMessage);
    return null;
  }
};

const ForgotPassword = () => {
  const navigation = useNavigation();
  const isSubmitting = navigation.state === "submitting";
  return (
    <section className="min-h-screen flex items-center justify-center bg-gradient-to-br from-base-100 to-base-300 px-4 py-12">
      <Form
        method="POST"
        className="card w-full max-w-md bg-base-100 shadow-2xl p-8 rounded-2xl flex flex-col gap-y-5"
      >
        <h4 className="text-center text-3xl font-extrabold text-primary">
          Forgot Password
        </h4>

        <FormInput type="email" label="email" name="email" />

        <div className="mt-4">
          <SubmitBtn
            text={isSubmitting ? "Submitting..." : "Submit"}
            disabled={isSubmitting}
          />
        </div>
      </Form>
    </section>
  );
};
export default ForgotPassword;
