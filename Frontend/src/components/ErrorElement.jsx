import { useRouteError } from "react-router-dom"

const ErrorElement = () => {
    const error = useRouteError()
    console.log(error);
    
  return (
    <h4 className="min-h-screen flex flex-col items-center justify-center px-6 py-12 text-6xl">
      There was an error...
    </h4>
  );
}
export default ErrorElement