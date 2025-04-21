import { Link } from "react-router-dom";
import main from "../assets/images/main.svg";

const LandingPage = () => {
  return (
    <section className=" min-h-screen">
      <div className="grid items-center min-h-[calc(100vh-6rem)] w-[90vw] max-w-[1120px] mx-auto px-4 md:px-8 lg:grid-cols-2">
        <div className="text-center lg:text-left space-y-6">
          <h1 className="text-4xl md:text-6xl font-extrabold leading-tight capitalize">
            Take charge of your <br />
            <span className="text-primary">personal finances</span>
          </h1>
          <p className="text-base md:text-lg leading-loose max-w-[35em] mx-auto lg:mx-0">
            Kharcha Monitor is your smart companion for managing money.
            Effortlessly track your income, monitor expenses, and get insightful
            summaries to stay in control and achieve your financial goals — all
            in one place.
          </p>
          <div className="flex flex-col md:flex-row gap-4 justify-center lg:justify-start">
            <Link
              to="/register"
              className="btn btn-primary px-6 py-2 text-base font-semibold capitalize shadow-md"
            >
              Get Started
            </Link>
            <Link
              to="/login"
              className="btn btn-outline btn-secondary px-6 py-2 text-base font-semibold capitalize shadow-md"
            >
              Login
            </Link>
          </div>
        </div>

        <div className="hidden lg:block">
          <img
            src={main}
            alt="Personal Finance Illustration"
            className="w-full object-cover"
          />
        </div>
      </div>
    </section>
  );
};

export default LandingPage;
