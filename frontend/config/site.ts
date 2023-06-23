export type SiteConfig = typeof siteConfig

export const siteConfig = {
  name: "SupplyControl+",
  mainNav: [
    {
      title: "Screening",
      href: "/screening",
    },
    {
      title: "Dashboard",
      href: "/",
    },
    {
      title: "Purchase Orders",
      href: "/orders",
    },
    {
      title: "Loans",
      href: "/loans",
    },
  ],
  links: {
    twitter: "https://twitter.com/shadcn",
    github: "https://github.com/shadcn/ui",
    docs: "https://ui.shadcn.com",
  },
}
