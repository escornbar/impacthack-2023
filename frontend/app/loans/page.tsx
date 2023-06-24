import { AlertOctagon, ChevronDown, Link } from "lucide-react"

import { Button } from "@/components/ui/button"
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"
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
    category: "Business Loan",
    totalAmount: 10000,
    outstandingAmount: 2500,
  },
]

export default function Loans() {
  return (
    <>
      <div className="flex-col md:flex">
        <div className="flex-1 py-8 pt-6 space-y-4">
          <div className="flex items-center justify-between space-y-2">
            <h2 className="text-3xl font-bold tracking-tight">Active Loans</h2>
          </div>
        </div>
        <div className="flex items-center justify-between space-y-2 overflow-x-auto">
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead className="">Loan Category</TableHead>
                <TableHead className="text-right">Total Amount (MYR)</TableHead>
                <TableHead className="text-right">
                  Outstanding Amount (MYR)
                </TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {loans.map((loan, index) => (
                <TableRow key={index}>
                  <TableCell className="font-medium">{loan.category}</TableCell>
                  <TableCell className="text-right">
                    {loan.totalAmount}
                  </TableCell>
                  <TableCell className="font-bold text-right text-destructive">
                    {loan.outstandingAmount}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>
        <div className="grid justify-end py-4">
          <Dialog>
            <DialogTrigger asChild>
              <Button variant={"ghost"}>
                <ChevronDown className="w-4 h-4 mr-2"/> View more
              </Button>
            </DialogTrigger>
            <DialogContent>
              <DialogHeader >
                  
                <AlertOctagon className="w-6 h-6" />
                <DialogTitle>Time to close your book!</DialogTitle>
                <DialogDescription>
                  Close your book now to continue using other features.
                </DialogDescription>
              </DialogHeader>
            </DialogContent>
          </Dialog>
        </div>
      </div>
    </>
  )
}
