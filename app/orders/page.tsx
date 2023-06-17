import { Order, columns } from "./columns"
import { DataTable } from "./data-table"

async function getData(): Promise<Order[]> {
  // Fetch data from your API here.
  return [
    {
      id: 1,
      orderdate: new Date('2023-06-17T10:30:00Z'),
      customer: "Ah Kok Berhad",
      items: [],
      total: 1000,
      downpayment: 30,
      remaining: 700,
      status: "Completed",
      tracking: "123456789",
      bank: "Down Payment Requested",
    },
    {
      id: 1,
      orderdate: new Date('2023-06-17T10:30:00Z'),
      customer: "Ah Kok Berhad",
      items: [],
      total: 3000,
      downpayment: 30,
      remaining: 200,
      status: "Completed",
      tracking: "123456789",
      bank: "Down Payment Requested",
    },
    {
      id: 1,
      orderdate: new Date('2023-06-17T10:30:00Z'),
      customer: "Ah Kok Berhad",
      items: [],
      total: 2000,
      downpayment: 30,
      remaining: 800,
      status: "Completed",
      tracking: "123456789",
      bank: "Down Payment Requested",
    },
    // ...
  ]
}

export default async function PurchaseOrder() {
  const data = await getData()
  
  return (
    <>
      <div className="flex-col md:flex">
        <div className="flex-1 py-8 pt-6 space-y-4">
          <div className="flex items-center justify-between space-y-2">
            <h2 className="text-3xl font-bold tracking-tight">
              Purchase Orders
            </h2>
          </div>
        </div>
        <div className="flex items-center justify-between space-y-2 overflow-x-auto">
          <DataTable columns={columns} data={data} />
        </div>
      </div>
    </>
  )
}
