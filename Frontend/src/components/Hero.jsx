import {
  FaEnvelopeOpenText,
  FaUser,
  FaMoneyBillWave,
  FaBell,
  FaChartBar,
} from "react-icons/fa";
import { BsFillBellFill, BsFillPieChartFill } from "react-icons/bs";
import { FeatureCard, Testimonial } from "../components";

const Hero = () => {
  return (
    <div className="">
      {/* Hero Section */}
      <header className="bg-base-300 text-center py-10 px-4">
        <h1 className="text-4xl font-extrabold">
          Smart Budgeting, Effortless Tracking, Smarter Savings!
        </h1>
        <p className="mt-4 text-lg">
          Stay on top of your income and expenses with real-time insights and
          alerts.
        </p>
      </header>

      {/* Why KharchaMonitor? */}
      <section className="py-12 px-6 max-w-5xl mx-auto text-center">
        <h2 className="text-3xl font-bold">Why KharchaMonitor?</h2>
        <div className="grid md:grid-cols-3 gap-8 mt-8 transition-colors duration-200">
          <FeatureCard
            icon={<BsFillPieChartFill className="text-primary" />}
            title="Smart Budgeting"
            description="Set a monthly budget and track your spending."
          />
          <FeatureCard
            icon={<BsFillBellFill className="text-primary" />}
            title="Expense Alerts"
            description="Get notified when you exceed your budget limit."
          />
          <FeatureCard
            icon={<FaEnvelopeOpenText className="text-primary" />}
            title="Monthly Report"
            description="Receive a detailed financial summary via email."
          />
        </div>
      </section>

      {/* How It Works */}
      <section className=" py-12 px-6 text-center">
        <h2 className="text-3xl font-bold ">How It Works</h2>
        <div className="max-w-4xl mx-auto mt-8 text-lg">
          <p className="flex items-center gap-2 justify-center">
            <FaUser className="text-primary" />
            <strong>Step 1:</strong> Sign up & set your monthly budget.
          </p>
          <p className="flex items-center gap-2 justify-center ">
            <FaMoneyBillWave className="text-primary" />
            <strong>Step 2:</strong> Add your income & daily expenses.
          </p>
          <p className="flex items-center gap-2 justify-center">
            <FaBell className="text-primary" />
            <strong>Step 3:</strong> Get real-time alerts when your expenses
            exceed your budget.
          </p>
          <p className="flex items-center gap-2 justify-center">
            <FaChartBar className="text-primary" />
            <strong>Step 4:</strong> Receive a monthly financial summary in your
            inbox.
          </p>
        </div>
      </section>

      {/* Testimonials */}
      <section className="py-12 px-6 max-w-5xl mx-auto text-center">
        <h2 className="text-3xl font-bold ">What Our Users Say</h2>
        <div className="grid md:grid-cols-3 gap-8 mt-8">
          <Testimonial
            name="Ankur Narendra Singh"
            text="I saved ₹10,000 in 3 months thanks to KharchaMonitor's alerts!"
          />
          <Testimonial
            name="Aniket Bisht"
            text="The budget tracking and insights helped me cut unnecessary expenses!"
          />
          <Testimonial
            name="Hariom Joshi"
            text="The monthly report gives me a clear picture of my spending!"
          />
        </div>
      </section>
    </div>
  );
};

export default Hero;
