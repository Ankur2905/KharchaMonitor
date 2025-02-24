import { Form } from "react-router-dom";
import { FormInput, SubmitBtn } from "../components";

const VerifyOtp = () => {
  return (
    <section className="h-screen grid place-items-center">
      <Form
        method="POST"
        className="card w-96 p-8 bg-base-300 shadow-lg  flex flex-col gap-y-4"
      >
        <h4 className="text-center text-3xl font-bold">Verify OTP</h4>
        <FormInput type="number" label="otp" name="otp" />
        <div className="mt-4">
          <SubmitBtn text="Submit" />
        </div>
      </Form>
    </section>
  );
};
export default VerifyOtp;
