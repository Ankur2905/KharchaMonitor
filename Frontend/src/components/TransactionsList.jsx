import { useLoaderData } from "react-router-dom"
import day from "dayjs"
import advanceFormat from "dayjs/plugin/advancedFormat";
day.extend(advanceFormat);

const TransactionsList = () => {
    const {transactions} = useLoaderData();
    
  return (
    <div className="mt-10 px-4 sm:px-8">
      <h4 className="mb-6 font-semibold capitalize text-base-content">
        Total Transactions:{" "}
        <span className="text-primary">{transactions.length}</span>
      </h4>

      <div className="overflow-x-auto rounded-xl shadow-md border border-base-200 bg-base-100">
        <table className="min-w-full text-sm">
          <thead className="bg-base-300 text-base-content uppercase tracking-wide text-xs">
            <tr>
              <th className="p-4 text-left font-semibold">Category</th>
              <th className="p-4 text-left font-semibold">Type</th>
              <th className="p-4 text-left font-semibold">Amount</th>
              <th className="p-4 text-left font-semibold">Date</th>
              <th className="p-4 text-left font-semibold hidden md:table-cell">
                Description
              </th>
            </tr>
          </thead>
          <tbody>
            {transactions.map(
              ({ id, category, type, amount, date, description }) => {
                const formattedDate = day(date).format("MMM Do, YYYY");

                return (
                  <tr
                    key={id}
                    className="hover:bg-base-200  even:bg-base-100 odd:bg-base-200"
                  >
                    <td className="p-4 capitalize text-base-content">
                      {category.toLowerCase()}
                    </td>
                    <td className="p-4 capitalize text-base-content">
                      {type.toLowerCase()}
                    </td>
                    <td
                      className={`p-4 font-semibold ${
                        type.toLowerCase() === "income"
                          ? "text-green-600 dark:text-green-400"
                          : "text-red-500 dark:text-red-400"
                      }`}
                    >
                      ₹{amount}
                    </td>
                    <td className="p-4 text-base-content">{formattedDate}</td>
                    <td className="p-4 hidden md:table-cell capitalize text-base-content/70">
                      {description?.trim() ? description : "—"}
                    </td>
                  </tr>
                );
              }
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}
export default TransactionsList