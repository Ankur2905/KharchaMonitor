import { Outlet } from "react-router-dom";

const HomeLayout = () => {
  return (
    <>
      <section>
        <Outlet />
      </section>
    </>
  );
};
export default HomeLayout;
