import { useLoaderData } from "react-router-dom"

const TransactionsList = () => {
    const {username} = useLoaderData()
    console.log(username);
    
  return (
    <div>TransactionsList</div>
  )
}
export default TransactionsList