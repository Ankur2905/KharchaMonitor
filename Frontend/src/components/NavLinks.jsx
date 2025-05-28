import { useSelector } from "react-redux";
import { NavLink } from "react-router-dom";

const links = [
  { id: 1, url: ".", text: "Dashboard" },
  { id: 2, url: "addTransaction", text: "Add Transaction" },
  { id: 3, url: "addBudget", text: "Budget Management" },
  { id: 4, url: "transactions", text: "Transactions" },
  { id: 5, url: "about", text: "About" },
];

const NavLinks = () => {
  const user = useSelector((state) => state.userState.user);
  return (
    <>
      {links.map((link) => {
        const { id, url, text } = link;
        if (
          (url === "." ||
            url === "addTransaction" ||
            url === "addBudget" ||
            url === "about" ||
            url === "transactions") &&
          !user
        )
          return null;
        return (
          <li key={id}>
            <NavLink
              className="capitalize hover:scale-105 duration-200 "
              to={url}
              end
            >
              {text}
            </NavLink>
          </li>
        );
      })}
    </>
  );
};
export default NavLinks;
