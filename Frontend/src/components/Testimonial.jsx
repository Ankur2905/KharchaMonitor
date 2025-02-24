const Testimonial = ({ name, text }) => {
  return (
    <div className=" p-6 rounded-lg shadow-md text-center border border-gray-200 ">
      <p className="italic">"{text}"</p>
      <p className="mt-2 font-medium ">{name}</p>
    </div>
  );
};

export default Testimonial;
