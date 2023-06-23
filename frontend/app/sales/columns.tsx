"use client"

import { ColumnDef } from "@tanstack/react-table"

// This type is used to define the shape of our data.
// You can use a Zod schema here if you want.
export type Sales = {
  id: number
  transactionDate: any
  transactionAmount: number
  refNo: string
  fromAccount: {
    accountName: string
    owner: {
      name: string
    }
  }
}

export const columns: ColumnDef<Sales>[] = [
  {
    accessorKey: "salesId",
    header: "Sales ID",
    cell: ({ row }) => {
      return <p className="text-right">{row.original.id}</p>
    },
  },
  {
    accessorKey: "transactionDate",
    header: "Transaction Date",
    cell: ({ row }) => {
      return <p>{row.original.transactionDate}</p>
    },
  },
  {
    accessorKey: "Customer",
    header: "Amount",
  },
]
