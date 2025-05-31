import { useRef } from "react";

const ConfirmModal = ({ message, onConfirm, onCancel }) => {
  const modalRef = useRef();

  const handleBackdropClick = (e) => {
    // If click is outside modal box, trigger onCancel
    if (modalRef.current && !modalRef.current.contains(e.target)) {
      onCancel();
    }
  };

  return (
    <div
      className="fixed inset-0 bg-black bg-opacity-50 z-50 flex items-center justify-center"
      onClick={handleBackdropClick}
    >
      <div
        ref={modalRef}
        className="bg-base-100 text-base-content p-6 rounded-xl shadow-2xl w-full max-w-sm animate-scaleIn"
        onClick={(e) => e.stopPropagation()} // prevent clicks inside modal from closing it
      >
        <h2 className="text-lg font-bold mb-3 text-center">Confirm Deletion</h2>
        <p className="mb-6 text-center text-sm opacity-80">{message}</p>
        <div className="flex justify-end gap-3">
          <button onClick={onCancel} className="btn btn-ghost">
            Cancel
          </button>
          <button onClick={onConfirm} className="btn btn-error">
            Delete
          </button>
        </div>
      </div>
    </div>
  );
};

export default ConfirmModal;
