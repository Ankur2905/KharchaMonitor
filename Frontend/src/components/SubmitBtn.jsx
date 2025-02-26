const SubmitBtn = ({ text, disabled }) => {
  return (
    <button type="submit" className="btn btn-block btn-primary uppercase" disabled={disabled}>
      {text}
    </button>
  );
};
export default SubmitBtn;
