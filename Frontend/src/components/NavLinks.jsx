import { NavLink } from "react-router-dom";

const links = [
  { id: 1, url: "/", text: "home" },
  { id: 2, url: "/dashboard", text: "Dashboard" },
  { id: 3, url: "/addBudget", text: "Budget Management" },
  { id: 4, url: "/addTransaction", text: "Add Transaction" },
  { id: 5, url: "/transactions", text: "Transactions" },
  { id: 6, url: "/about", text: "About" },
];

const NavLinks = () => {
  return (
    <>
      {links.map((link) => {
        const { id, url, text } = link;
        return (
          <li key={id}>
            <NavLink
              className="capitalize hover:scale-105 duration-200 "
              to={url}
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
