import { Form, redirect, useSearchParams } from "react-router-dom";
import { FormInput, SubmitBtn } from "../components";
import { customFetch } from "../utils";
import { toast } from "react-toastify";

export const action = async ({ request }) => {
  const formData = await request.formData();
  const data = Object.fromEntries(formData);

  try {
    const response = await customFetch.post(
      `/auth/verify?email=${encodeURIComponent(
        data.email
      )}&otp=${encodeURIComponent(data.otp)}`
    );
    toast.success("OTP verified successfully");
    return redirect("/login");
  } catch (error) {
    const errorMessage =
      error?.response?.data?.error?.message ||
      "please double check your credentials";
    toast.error(errorMessage);
    return null;
  }
};

const VerifyOtp = () => {
  const [searchParams] = useSearchParams();
  const email = searchParams.get("email"); // Get email from URL

  return (
    <section className="h-screen grid place-items-center">
      <Form
        method="POST"
        className="card w-96 p-8 bg-base-300 shadow-lg  flex flex-col gap-y-4"
      >
        <h4 className="text-center text-3xl font-bold">Verify OTP</h4>
        <FormInput type="hidden" name="email" value={email} />
        <FormInput type="text" label="OTP" name="otp" />
        <div className="mt-4">
          <SubmitBtn text="Submit" />
        </div>
      </Form>
    </section>
  );
};
export default VerifyOtp;
