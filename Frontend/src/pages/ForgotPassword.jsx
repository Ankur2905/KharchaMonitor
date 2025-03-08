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
    <section className="h-screen grid place-items-center">
      <Form
        method="POST"
        className="card w-96 p-8 bg-base-300 shadow-lg  flex flex-col gap-y-4"
      >
        <h4 className="text-center text-3xl font-bold">Forgot Password</h4>
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
