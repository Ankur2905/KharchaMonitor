const About = () => {
  return (
    <section className="min-h-screen flex flex-col items-center justify-center  px-6 py-12">
      <div className="max-w-3xl text-center shadow-lg rounded-2xl p-8 border border-gray-300">
        <h2 className="text-4xl font-bold text-primary mb-4">
          About KharchaMonitor
        </h2>
        <p className="text-lg  leading-relaxed">
          <span className="font-semibold">KharchaMonitor</span> is a smart and
          intuitive
          <span className="text-primary">
            {" "}
            expense tracking application
          </span>{" "}
          designed to help you manage your finances effortlessly. Whether you
          want to track your daily expenses, monitor your monthly budget, or get
          insights into your spending habits,
          <span className="font-semibold"> KharchaMonitor</span> provides a
          simple and effective way to stay in control of your money.
        </p>

        <div className="mt-6 text-left">
          <h3 className="text-2xl font-bold mb-3">
            Key Features:
          </h3>
          <ul className="list-disc list-inside space-y-2">
            <li>
              <span className="font-semibold text-primary">Track Expenses</span>
              {" - "}
              Log your daily spending with ease.
            </li>
            <li>
              <span className="font-semibold text-primary">
                Borrow & Lend Money
              </span>
              {" - "}
              Keep records of borrowed and lent amounts.
            </li>
            <li>
              <span className="font-semibold text-primary">
                Monthly Summaries
              </span>
              {" - "}
              Get an overview of your financial health.
            </li>
            <li>
              <span className="font-semibold text-primary">
                Payment Reminders
              </span>
              {" - "}
              Never miss a due date with timely notifications.
            </li>
            <li>
              <span className="font-semibold text-primary">
                Secure & User-Friendly
              </span>
              {" - "}
              Your data is safe and easily accessible.
            </li>
          </ul>
        </div>

        <p className="mt-6 text-lg  font-semibold">
          With <span className="text-primary font-bold">KharchaMonitor</span>,
          managing money is no longer a hassle. Take charge of your financial
          future today!
        </p>
      </div>
    </section>
  );
};
export default About;
