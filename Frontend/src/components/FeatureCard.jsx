const FeatureCard = ({ icon, title, description }) => {
  return (
    <div className="bg-base-100 p-6 rounded-lg shadow-md text-center border border-gray-200 hover:shadow-lg transition duration-300">
      <div className="text-4xl mb-4 flex justify-center">
        {icon}
      </div>
      <h3 className="text-xl font-bold">{title}</h3>
      <p className="mt-2">{description}</p>
    </div>
  );
};

export default FeatureCard;
