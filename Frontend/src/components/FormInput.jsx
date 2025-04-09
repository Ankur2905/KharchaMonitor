const FormInput = ({ label, name, type, size, value }) => {
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
      />
    </div>
  );
};
export default FormInput;
