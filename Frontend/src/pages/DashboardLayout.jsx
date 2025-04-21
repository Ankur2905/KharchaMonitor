import { Outlet } from "react-router-dom"
import { Header, Navbar } from "../components"

const DashboardLayout = () => {
  return (
    <section>
      <>
        <div>
          <Header />
          <Navbar />
        </div>
        <div className="w-[90vw] mx-auto py-8">
          <Outlet />
        </div>
      </>
    </section>
  );
}
export default DashboardLayout