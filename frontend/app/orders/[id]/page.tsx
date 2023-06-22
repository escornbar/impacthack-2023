import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"

const testobject = {
  id: 1,
  orderdate: new Date("2023-06-17T10:30:00Z"),
  customer: "Ah Kok Berhad",
  items: [
    {
      id: 1,
      name: "Item 1",
      unitprice: 100,
      quantity: 10,
      total: 1000,
    },
    {
      id: 2,
      name: "Item 2",
      unitprice: 100,
      quantity: 10,
      total: 1000,
    },
  ],
  total: 1000,
  downpayment: 30,
  remaining: 700,
  status: "Completed",
  tracking: "123456789",
  bank: "Down Payment Requested",
}

export default function ViewOrder({ searchParams }) {
  return (
    <>
      <div className="flex-col md:flex">
        <div className="flex-1 py-8 pt-6 space-y-4">
          <div className="flex items-center justify-between space-y-2">
            {/* <h2 className="text-3xl font-bold tracking-tight">
              Purchase Orders
            </h2> */}
          </div>
        </div>
        <div className="flex items-center justify-between space-y-2">
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead className="]">Item</TableHead>
                <TableHead>Quantity</TableHead>
                <TableHead>Unit Price</TableHead>
                <TableHead className="text-right">Total Amount</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {testobject.items.map((item) => (
                <TableRow key={item.id}>
                  <TableCell className="font-medium">{item.name}</TableCell>
                  <TableCell>{item.quantity}</TableCell>
                  <TableCell>{item.unitprice}</TableCell>
                  <TableCell className="text-right">
                    {item.quantity * item.unitprice}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>
          <div className="grid justify-end">
            <h3 className="text-2xl font-semibold tracking-tight scroll-m-20">
              Grand Total: <span>{}</span>
            </h3>
          </div>
      </div>
    </>
  )
}
