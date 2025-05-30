import { FaPiggyBank, FaWallet, FaMoneyBillWave } from "react-icons/fa";

const StatCard = ({ icon: Icon, title, amount, bgColor, textColor }) => (
  <div
    className={`flex flex-col justify-between p-6 rounded-2xl shadow-lg ${bgColor} ${textColor} transition-transform transform hover:scale-[1.01]`}
  >
    <div className="text-5xl mb-4">
      <Icon />
    </div>
    <div>
      <p className="text-lg font-semibold mb-1">{title}</p>
      <p className="text-3xl font-bold">₹{amount}</p>
    </div>
  </div>
);

const DashboardStats = ({ budget, income, expenses }) => {
  return (
    <div className=" py-10 px-6">
      <div className="max-w-7xl mx-auto h-full grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
        <StatCard
          icon={FaPiggyBank}
          title="Monthly Budget"
          amount={budget}
          bgColor="bg-gradient-to-br from-blue-100 to-blue-300"
          textColor="text-blue-900"
        />
        <StatCard
          icon={FaWallet}
          title="Total Monthly Income"
          amount={income}
          bgColor="bg-gradient-to-br from-green-100 to-green-300"
          textColor="text-green-900"
        />
        <StatCard
          icon={FaMoneyBillWave}
          title="Total Monthly Expenses"
          amount={expenses}
          bgColor="bg-gradient-to-br from-red-100 to-red-300"
          textColor="text-red-900"
        />
      </div>
    </div>
  );
};

export default DashboardStats;
