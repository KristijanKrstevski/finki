using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace EShop.Web.Data.Migrations
{
    /// <inheritdoc />
    public partial class InitialCreate : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ProductShoppingCarts_Products_ProductId",
                table: "ProductShoppingCarts");

            migrationBuilder.DropForeignKey(
                name: "FK_ProductShoppingCarts_ShoppingCarts_ShoppingCartId",
                table: "ProductShoppingCarts");

            migrationBuilder.AddForeignKey(
                name: "FK_ProductShoppingCarts_Products_ShoppingCartId",
                table: "ProductShoppingCarts",
                column: "ShoppingCartId",
                principalTable: "Products",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ProductShoppingCarts_ShoppingCarts_ProductId",
                table: "ProductShoppingCarts",
                column: "ProductId",
                principalTable: "ShoppingCarts",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ProductShoppingCarts_Products_ShoppingCartId",
                table: "ProductShoppingCarts");

            migrationBuilder.DropForeignKey(
                name: "FK_ProductShoppingCarts_ShoppingCarts_ProductId",
                table: "ProductShoppingCarts");

            migrationBuilder.AddForeignKey(
                name: "FK_ProductShoppingCarts_Products_ProductId",
                table: "ProductShoppingCarts",
                column: "ProductId",
                principalTable: "Products",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ProductShoppingCarts_ShoppingCarts_ShoppingCartId",
                table: "ProductShoppingCarts",
                column: "ShoppingCartId",
                principalTable: "ShoppingCarts",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
