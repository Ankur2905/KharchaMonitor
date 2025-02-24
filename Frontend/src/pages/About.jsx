const About = () => {
  return (
    <section className="min-h-screen flex flex-col items-center justify-center px-6 py-12">
      {/* Title Section */}
      <div className="flex flex-col sm:flex-row items-center justify-center gap-4 sm:gap-6">
        <h1 className="text-4xl font-bold leading-none tracking-tight sm:text-6xl">
          We love
        </h1>
        <div className="bg-primary shadow px-6 py-3 rounded-lg">
          <h2 className="text-primary-content text-4xl font-bold tracking-widest">
            KharchaMonitor
          </h2>
        </div>
      </div>

      {/* Description */}
      <div className="max-w-2xl mx-auto mt-6 text-center">
        <p className="text-lg leading-relaxed">
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
      </div>

      {/* Key Features */}
      <div className="max-w-2xl mx-auto mt-8">
        <h3 className="text-2xl font-bold mb-4 text-center">Key Features</h3>
        <ul className="list-disc list-inside space-y-3 text-left">
          <li>
            <span className="font-semibold text-primary">Track Expenses</span> -
            Log your daily spending with ease.
          </li>
          <li>
            <span className="font-semibold text-primary">
              Borrow & Lend Money
            </span>{" "}
            - Keep records of borrowed and lent amounts.
          </li>
          <li>
            <span className="font-semibold text-primary">
              Monthly Summaries
            </span>{" "}
            - Get an overview of your financial health.
          </li>
          <li>
            <span className="font-semibold text-primary">
              Payment Reminders
            </span>{" "}
            - Never miss a due date with timely notifications.
          </li>
          <li>
            <span className="font-semibold text-primary">
              Secure & User-Friendly
            </span>{" "}
            - Your data is safe and easily accessible.
          </li>
        </ul>
      </div>

      {/* Final CTA */}
      <p className="mt-8 text-lg font-semibold max-w-2xl mx-auto text-center">
        With <span className="text-primary font-bold">KharchaMonitor</span>,
        managing money is no longer a hassle. Take charge of your financial
        future today!
      </p>
    </section>
  );
};

export default About;
