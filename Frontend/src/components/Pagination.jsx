import { useLoaderData, useLocation, useNavigate } from "react-router-dom";

const Pagination = () => {
  const { data } = useLoaderData();
  const { activePage, totalPage } = data;

  const { search, pathname } = useLocation();
  const navigate = useNavigate();

  const handleChange = (pageNumber) => {
    const searchParams = new URLSearchParams(search);
    searchParams.set("page", pageNumber - 1);
    navigate(`${pathname}?${searchParams.toString()}`);
  };

  const addPageButton = ({ pageNumber, activeClass }) => {
    return (
      <button
        key={pageNumber}
        onClick={() => handleChange(pageNumber)}
        className={`btn btn-xs sm:btn-md border-none join-item ${
          activeClass ? "bg-base-300 border-base-300" : ""
        }`}
      >
        {pageNumber}
      </button>
    );
  };

  const renderPageButtons = () => {
    const pageButtons = [];
    // first button
    pageButtons.push(
      addPageButton({ pageNumber: 1, activeClass: activePage === 1 })
    );

    // dots
    if (activePage > 2) {
      pageButtons.push(
        <button className="join-item btn btn-xs sm:btn-md" key="dots-1">
          ...
        </button>
      );
    }

    // active page
    if (activePage != 1 && activePage != totalPage) {
      pageButtons.push(
        addPageButton({ pageNumber: activePage, activeClass: true })
      );
    }

    // dots
    if (activePage < totalPage - 1) {
      pageButtons.push(
        <button className="join-item btn btn-xs sm:btn-md" key="dots-2">
          ...
        </button>
      );
    }

    // last button
    pageButtons.push(
      addPageButton({
        pageNumber: totalPage,
        activeClass: activePage === totalPage,
      })
    );
    return pageButtons;
  };

  if (totalPage < 2) {
    return null;
  }

  return (
    <div className="mt-16 flex justify-end">
      <div className="join">
        <button
          className="btn btn-xs sm:btn-md join-item"
          onClick={() => handleChange(activePage - 1)}
          disabled={activePage === 1}
        >
          Prev
        </button>
        {renderPageButtons()}
        <button
          className="btn btn-xs sm:btn-md join-item"
          onClick={() => handleChange(activePage + 1)}
          disabled={activePage === totalPage}
        >
          Next
        </button>
      </div>
    </div>
  );
};
export default Pagination;
