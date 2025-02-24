import { Outlet } from "react-router-dom";
import { Footer, Header, Navbar } from "../components";

const HomeLayout = () => {
  return (
    <>
      <Header />
      <Navbar />
      <section className="align-element">
        <Outlet />
      </section>
      <Footer />
    </>
  );
};
export default HomeLayout;
