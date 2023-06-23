import { Badge } from "@/components/ui/badge"
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"

async function getInvoice(id: number) {
  const res = await fetch(
    "http://ec2-13-215-176-102.ap-southeast-1.compute.amazonaws.com:8080/api/invoices/" +
      id
  )
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  // Recommendation: handle errors
  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error("Failed to fetch data")
  }

  return res.json()
}

async function getTransactions(id: number) {
  const res = await fetch(
    "http://ec2-13-215-176-102.ap-southeast-1.compute.amazonaws.com:8080/api/transactions?invoiceId=" +
      id
  )
  // The return value is *not* serialized
  // You can return Date, Map, Set, etc.

  // Recommendation: handle errors
  if (!res.ok) {
    // This will activate the closest `error.js` Error Boundary
    throw new Error("Failed to fetch data")
  }

  return res.json()
}

export default async function View({ params }: { params: { id: number } }) {
  const data = await getInvoice(params.id)
  const transactions = await getTransactions(params.id)
  return (
    <>
      <div className="flex-col md:flex">
        <div className="flex-1 py-8 pt-6 space-y-4">
          <div className="flex items-center justify-between space-y-2">
            <h2 className="text-3xl font-bold tracking-tight">
              Invoice for PO-{params.id}
            </h2>
          </div>
        </div>
        <div className="flex items-center justify-between space-y-2">
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead className="">Invoice ID</TableHead>
                <TableHead>Total</TableHead>
                <TableHead>Issued Date</TableHead>
                <TableHead>Deadline</TableHead>
                <TableHead>Status</TableHead>
                <TableHead>Down Payment (%)</TableHead>
                <TableHead>Remaining Payment</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              <TableRow key={data.invoiceId}>
                <TableCell>{data.invoiceId}</TableCell>
                <TableCell>{data.total}</TableCell>
                <TableCell>{data.issuedDate}</TableCell>
                <TableCell>{data.paymentDeadline}</TableCell>
                <TableCell>
                  <Badge>{data.invoiceStatus.name}</Badge>
                </TableCell>
                <TableCell>{data.downPayment}</TableCell>
                <TableCell>{data.remainingPayment}</TableCell>
              </TableRow>
            </TableBody>
          </Table>
        </div>
        <div className="flex-1 mt-10 space-y-4">
          <div className="flex items-center justify-between space-y-2">
            <h2 className="text-3xl font-bold tracking-tight">Transactions</h2>
          </div>
        </div>
        <div className="grid grid-cols-3 gap-8 py-10">
          {transactions.map((transaction: any) => (
            <Card>
              <CardHeader>
                <CardTitle>Transaction {transaction.transactionId}</CardTitle>
                <CardDescription>{transaction.transactionDate}</CardDescription>
              </CardHeader>
              <CardContent>
                <div className="grid grid-cols-2 pb-4">
                  <h2 className="pb-4 text-3xl font-bold tracking-tight">
                    MYR {transaction.transactionAmount}
                  </h2>
                  <div>
                    
                  <p className="text-sm text-muted-foreground">
                    Repayment Rate: {transaction.repaymentRate}
                  </p>
                  <p className="text-sm text-muted-foreground">
                    After Repayment: MYR {transaction.transactionAmountAfterRepayment}
                  </p>
                  </div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                  <Card>
                    <CardHeader>
                      <CardTitle>Sender</CardTitle>
                    </CardHeader>
                    <CardContent>
                      <p>{transaction.fromAccount.accountNumber}</p>
                      <p>{transaction.fromAccount.accountName}</p>
                    </CardContent>
                  </Card>
                  <Card>
                    <CardHeader>
                      <CardTitle>Recipient</CardTitle>
                    </CardHeader>
                    <CardContent>
                      <p>{transaction.toAccount.accountNumber}</p>
                      <p>{transaction.toAccount.accountName}</p>
                    </CardContent>
                  </Card>
                </div>
              </CardContent>
            </Card>
          ))}
        </div>
      </div>
    </>
  )
}
