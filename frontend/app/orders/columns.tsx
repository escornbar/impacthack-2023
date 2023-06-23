"use client";

import Link from "next/link";
import { ColumnDef } from "@tanstack/react-table";
import { ArrowUpDown, MoreHorizontal } from "lucide-react";



import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuLabel, DropdownMenuSeparator, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";





// export type Item = {
//   id: number
//   name: string
//   unitprice: number
//   quantity: number
//   total: number
// }

export type PO = {
  poId: number
  total: number
  orderDate: Date
  supplier: string
  distributor: string
  purchaseOrderStatus: string
  invoice: Invoice
}

export type Invoice = {
  invoiceId: number
  total: number
  issuedDate: Date
  paymentDeadline: Date
  status: string
  downPayment: number
  remainingPayment: number
}

export const columns: ColumnDef<PO>[] = [
  {
    accessorKey: "poId",
    header: "Order ID",
    cell: ({ row }) => {
      return <p className="text-right">{row.original.poId}</p>
    },
  },
  {
    accessorKey: "orderDate",
    header: "Date Placed",
    // cell: ({ row }) => {
    //   return <p>{row.original.orderDate}</p>
    // },
  },
  { accessorKey: "distributor.name", header: "Customer" },
  {
    accessorKey: "total",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Total Amount
          <ArrowUpDown className="w-4 h-4 ml-2" />
        </Button>
      )
    },
    cell: ({ row }) => {
      const amount = row.original.total
      const formatted = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "MYR",
      }).format(amount)

      return <div className="font-medium text-center">{formatted}</div>
    },
  },
  {
    accessorKey: "invoice.downPayment",
    header: "Down Payment (%)",
    cell: ({ row }) => {
      return <p className="text-center">{row.original.invoice.downPayment}</p>
    },
  },
  {
    accessorKey: "invoice.remainingPayment",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Remaining Amount
          <ArrowUpDown className="w-4 h-4 ml-2" />
        </Button>
      )
    },
    cell: ({ row }) => {
      const amount = row.original.invoice.remainingPayment
      const formatted = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "MYR",
      }).format(amount)

      return (
        <div className="font-bold text-center text-destructive">
          {formatted}
        </div>
      )
    },
  },
  {
    accessorKey: "purchaseOrderStatus.name",
    header: "Order Status",
    cell: ({ row }) => {
      return (
        <Badge
          variant={
            row.original.purchaseOrderStatus.name == "COMPLETED"
              ? "success"
              : "default"
          }
        >
          {row.original.purchaseOrderStatus.name}
        </Badge>
      )
    },
  },
  {
    accessorKey: "tracking",
    header: "Tracking Number",
    cell: ({ row }) => {
      return <p className="text-center">{row.original.poId == 102 ? 123456789 : 832457863}</p>
    },
  },
  // { accessorKey: "bank", header: "Bank Status" },
  {
    id: "actions",
    cell: ({ row }) => {
      return (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="w-8 h-8 p-0">
              <span className="sr-only">Open menu</span>
              <MoreHorizontal className="w-4 h-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuLabel>Actions</DropdownMenuLabel>
            <DropdownMenuSeparator />
            {/* <DropdownMenuItem asChild><Link href={`orders/${row.original.id}`}>View order details</Link></DropdownMenuItem> */}
            <DropdownMenuItem asChild>
              <Link href={`/orders/view/${row.original.invoice.invoiceId}`}>
                View invoice
              </Link>
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      )
    },
  },
]