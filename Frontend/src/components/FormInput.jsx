const FormInput = ({ label, name, type, size, value, required }) => {
  return (
    <div className="form-control">
      <label htmlFor={name} className="label">
        <span className="label-text capitalize ">{label}</span>
      </label>
      <input
        type={type}
        name={name}
        id={name}
        className={`input input-bordered ${size}`}
        defaultValue={value}
        required = {required}
      />
    </div>
  );
};
export default FormInput;
