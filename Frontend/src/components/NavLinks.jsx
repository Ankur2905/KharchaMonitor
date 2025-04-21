import { useSelector } from "react-redux";
import { NavLink } from "react-router-dom";

const links = [
  { id: 1, url: ".", text: "Add Transaction" },
  { id: 2, url: "addBudget", text: "Budget Management" },
  { id: 3, url: "transactions", text: "Transactions" },
  { id: 4, url: "about", text: "About" },
];

const NavLinks = () => {
  const user = useSelector((state) => state.userState.user);
  return (
    <>
      {links.map((link) => {
        const { id, url, text } = link;
        if (
          (url === "." ||
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
