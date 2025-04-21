import { Link, useRouteError } from "react-router-dom";

const Error = () => {
  const error = useRouteError();
  console.log(error);

  if (error.status === 404) {
    return (
      <main className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
        <div className="text-center">
          <h1 className="text-6xl font-bold text-gray-800">404</h1>
          <h2 className="text-2xl mt-2 text-gray-700">Page Not Found</h2>
          <p className="mt-4 text-gray-600">
            Sorry, we couldn't find the page you're looking for.
          </p>
          <Link
            to="/"
            className="inline-block mt-6 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
          >
            Go Home
          </Link>
        </div>
      </main>
    );
  }

  return (
    <main className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
      <div className="text-center">
        <h2 className="text-2xl font-semibold text-gray-800">
          Something went wrong
        </h2>
        <p className="mt-2 text-gray-600">Please try again later.</p>
        <Link
          to="/"
          className="inline-block mt-6 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
        >
          Go Home
        </Link>
      </div>
    </main>
  );
};

export default Error;
