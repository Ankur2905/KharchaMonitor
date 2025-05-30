import { Outlet, useNavigation } from "react-router-dom";
import { Header, Navbar, Loading } from "../components";

const DashboardLayout = () => {
  const navigation = useNavigation();
  const isPageLoading = navigation.state === "loading";
  return (
    <section>
      <>
        <div>
          <Header />
          <Navbar />
        </div>
        {isPageLoading ? (
          <Loading />
        ) : (
          <div className="w-[90vw] mx-auto py-8">
            <Outlet />
          </div>
        )}
      </>
    </section>
  );
};
export default DashboardLayout;
