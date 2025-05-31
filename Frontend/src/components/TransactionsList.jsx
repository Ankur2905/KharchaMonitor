import { Link, useLoaderData } from "react-router-dom";
import { FiEdit, FiTrash2 } from "react-icons/fi";
import day from "dayjs";
import advanceFormat from "dayjs/plugin/advancedFormat";
import { customFetch } from "../utils";
import { useState } from "react";
import { toast } from "react-toastify";
import ConfirmModal from "../components/ConfirmModal"; // Import the modal

day.extend(advanceFormat);

const TransactionsList = () => {
  const { data } = useLoaderData();
  const [transactions, setTransactions] = useState(data.transactions);
  const [deleteId, setDeleteId] = useState(null);

  const handleDelete = async (id) => {
    try {
      const response = await customFetch.delete(`/transactions/${id}`);
      if (response.data.success) {
        setTransactions((prev) => prev.filter((txn) => txn.id !== id));
        toast.success("Transaction deleted successfully.");
      }
    } catch (error) {
      toast.error("Server error while deleting transaction");
      console.error(error);
    }
  };

  const confirmDelete = (id) => {
    setDeleteId(id); // Show modal
  };

  const cancelDelete = () => {
    setDeleteId(null); // Hide modal
  };

  const proceedDelete = () => {
    handleDelete(deleteId);
    setDeleteId(null);
  };

  return (
    <div className="mt-10 px-4 sm:px-8">
      <h4 className="mb-6 font-semibold capitalize text-base-content">
        Total Transactions:{" "}
        <span className="text-primary">{data.totalElements}</span>
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
              <th className="p-4 text-left font-semibold">Actions</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map(
              ({ id, category, type, amount, date, description }) => {
                const formattedDate = day(date).format("MMM Do, YYYY");

                return (
                  <tr
                    key={id}
                    className="hover:bg-base-200 even:bg-base-100 odd:bg-base-200"
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
                    <td className="p-4 flex gap-3 text-lg">
                      <Link
                        to={`../edit-transaction/${id}`}
                        className="text-blue-600 hover:text-blue-800"
                      >
                        <FiEdit />
                      </Link>
                      <button
                        onClick={() => confirmDelete(id)}
                        className="text-red-600 hover:text-red-800"
                      >
                        <FiTrash2 />
                      </button>
                    </td>
                  </tr>
                );
              }
            )}
          </tbody>
        </table>
      </div>

      {deleteId && (
        <ConfirmModal
          message="Are you sure you want to delete this transaction? This action cannot be undone."
          onConfirm={proceedDelete}
          onCancel={cancelDelete}
        />
      )}
    </div>
  );
};

export default TransactionsList;
