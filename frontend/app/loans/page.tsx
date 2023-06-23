import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"

const loans = [
  {
    category: "Working Capital Loan",
    totalAmount: 5000,
    outstandingAmount: 1000,
  },
  {
    category: "Working Capital Loan",
    totalAmount: 5000,
    outstandingAmount: 1000,
  },
  {
    category: "Working Capital Loan",
    totalAmount: 5000,
    outstandingAmount: 1000,
  },
]

export default function Loans() {
  return (
    <>
      <div className="flex-col md:flex">
        <div className="flex-1 space-y-4 py-8 pt-6">
          <div className="flex items-center justify-between space-y-2">
            <h2 className="text-3xl font-bold tracking-tight">Active Loans</h2>
          </div>
        </div>
        <div className="flex items-center justify-between space-y-2 overflow-x-auto">
          <Table>
            <TableCaption>A list of your active loans</TableCaption>
            <TableHeader>
              <TableRow>
                <TableHead className="">Loan Category</TableHead>
                <TableHead className="text-right">Total Amount</TableHead>
                <TableHead className="text-right">Outstanding Amount</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {loans.map((loan, index) => (
                <TableRow key={index}>
                  <TableCell className="font-medium">{loan.category}</TableCell>
                  <TableCell className="text-right">
                    {loan.totalAmount}
                  </TableCell>
                  <TableCell className="text-destructive text-right">
                    {loan.outstandingAmount}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>
      </div>
    </>
  )
}
