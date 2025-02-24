import { Link, useRouteError } from "react-router-dom";

const Error = () => {
  const error = useRouteError();
  console.log(error);

  if (error.status === 404) {
    return (
      <main
        className="min-h-screen flex flex-col items-center justify-center bg-gray-300 text-gray-900 px-6  "
        style={{
          backgroundImage: "url('/error.webp')",
          backgroundSize: "cover",
          backgroundPosition: "center",
          backgroundRepeat: "no-repeat",
        }}
      >
        <div className="text-center bg-white shadow-md rounded-lg p-10 border border-gray-400 backdrop-blur bg-white/0 ">
          <p className="text-9xl font-extrabold text-warning">404</p>
          <h1 className="mt-4 text-3xl font-bold sm:text-5xl">
            Page not found
          </h1>
          <p className="mt-4 text-lg text-gray-950">
            Sorry, we couldn't find the page you're looking for.
          </p>
          <div className="mt-6">
            <Link to="/" className="btn btn-info uppercase text-gray-900">
              Go back home
            </Link>
          </div>
        </div>
      </main>
    );
  }

  return (
    <main className="min-h-screen flex flex-col items-center justify-center bg-gray-100 text-gray-900 px-6">
      <div className="text-center bg-white shadow-md rounded-lg p-10 border border-gray-300">
        <h4 className="text-4xl font-bold text-gray-800">There was an error</h4>
      </div>
    </main>
  );
};

export default Error;
